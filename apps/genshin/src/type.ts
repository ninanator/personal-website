export enum Vision {
  ANEMO = 'Anemo',
  DENDRO = 'Dendro',
  ELECTRO = 'Electro',
  GEO = 'Geo',
  HYDRO = 'Hydro',
  PYRO = 'pyro',
}

export enum WeaponType {
  BOW = 'Bow',
  CATALYST = 'Catalyst',
  POLEARM = 'Polearm',
  SWORD = 'Sword',
}

export enum MainStatType {
  ANEMO_DMG = 'Anemo DMG',
  ATK_PERCENT = 'ATK%',
  CRIT_DMG = 'Crit DMG',
  CRIT_RATE = 'Crit Rate',
  CRYO_DMG = 'Cryo DMG',
  DEF_PERCENT = 'DEF%',
  DENDRO_DMG = 'Dendro DMG',
  ELECTRO_DMG = 'Electro DMG',
  ELEM_MASTERY = 'Elemental Mastery',
  ENERGY_RECHARGE = 'Energy Recharge',
  HEALING_BONUS = 'Healing Bonus',
  HP_PERCENT = 'HP%',
  HYDRO_DMG = 'Hydro DMG',
  PHYSICAL_DMG = 'Physical DMG',
  PYRO_DMG = 'Pyro DMG',
}

export enum SubStatType {
  ATK_PERCENT = 'ATK%',
  CRIT_DMG = 'Crit DMG',
  CRIT_RATE = 'Crit Rate',
  DEF_PERCENT = 'DEF%',
  ELEM_MASTERY = 'Elemental Mastery',
  ENERGY_RECHARGE = 'Energy Recharge',
  FLAT_ATK = 'Flat ATK',
  FLAT_DEF = 'Flat DEF',
  HP_PERCENT = 'HP%',
}

export type GenshinArtifact = {
  image?: string;
  name: string;
};

export type GenshinArtifactSet = {
  artifact1: GenshinArtifact;
  artifact1Count: number;
  artifact2?: GenshinArtifact;
  artifact2Count?: number;
}

export type GenshinWeapon = {
  image?: string;
  name: string;
  rarity: number;
}

export type GenshinCharacter = {
  avatar: string;
  name: string;
  role: string;
  vision: Vision;
  weaponType: WeaponType;
  topWeapons: GenshinWeapon[];
  topArtifacts: GenshinArtifactSet[];
  circletStat: MainStatType[];
  gobletStat: MainStatType[];
  sandsStat: MainStatType[];
  substats: SubStatType[];
  notes: string;
};

