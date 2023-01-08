import 'server-only';
import {
  Asset,
  ContentfulClientApi,
  createClient,
  Entry,
  EntryCollection,
} from 'contentful';
import {
  GenshinArtifact,
  GenshinCharacter,
  GenshinWeapon,
  MainStatType,
  SubStatType,
  Vision,
  WeaponType,
} from '../type';

type ContentfulGenshinArtifact = {
  image?: string;
  name: string;
};

type ContentfulGenshinArtifactSet = {
  artifact1: Entry<ContentfulGenshinArtifact>;
  artifact1Count: number;
  artifact2?: Entry<ContentfulGenshinArtifact>;
  artifact2Count?: number;
};

type ContentfulGenshinWeapon = {
  image?: string;
  name: string;
  rarity: number;
}

type ContentfulGenshinCharacter = {
  avatar: Asset;
  name: string;
  role: string;
  vision: Vision;
  weaponType: WeaponType;
  topWeapons: Entry<ContentfulGenshinWeapon>[];
  topArtifacts: Entry<ContentfulGenshinArtifactSet>[];
  circletStat: MainStatType[];
  gobletStat: MainStatType[];
  sandsStat: MainStatType[];
  substats: SubStatType[];
  notes: string;
};

const client: ContentfulClientApi = createClient({
  space: process.env.CONTENTFUL_SPACE_ID as string,
  accessToken: process.env.CONTENTFUL_ACCESS_TOKEN as string,
});

const prependHttps = (partialUrl: string): string => `https:${partialUrl}`;

// export async function getArtifacts(): Promise<GenshinArtifact[]> {
//   const artifacts: EntryCollection<ContentfulGenshinArtifact> =
//     await client.getEntries({
//       content_type: process.env.CONTENTFUL_ARTIFACT_TYPE_ID,
//     });
//
//   console.log(artifacts);
//
//   return artifacts.items.map(({ fields }) => fields);
// }

export async function getCharacters(): Promise<GenshinCharacter[]> {
  const characters: EntryCollection<ContentfulGenshinCharacter> =
    await client.getEntries({
      content_type: process.env.CONTENTFUL_CHARACTER_TYPE_ID,
      include: 2,
    });

  return characters.items.map(({ fields }) => ({
    ...fields,
    avatar: prependHttps(fields.avatar.fields.file.url),
    topWeapons: (fields.topWeapons || []).map(({ fields: weaponFields }) => ({
      name: weaponFields.name,
      rarity: weaponFields.rarity,
    })),
    topArtifacts: (fields.topArtifacts || []).map(({ fields: artifactFields }) => ({
      artifact1: {
        name: artifactFields.artifact1.fields.name,
      },
      artifact1Count: artifactFields.artifact1Count,
      ...(artifactFields.artifact2 ? {
        artifact2: {
          name: artifactFields.artifact2.fields.name,
        },
        artifact2Count: artifactFields.artifact2Count,
      } : {}),
    })),
  }));
}

// export async function getWeapons(): Promise<GenshinWeapon> {
//
// }
