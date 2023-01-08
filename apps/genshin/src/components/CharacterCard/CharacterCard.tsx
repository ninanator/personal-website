import { GenshinCharacter } from '../../type';
import Image from 'next/image';
import { classnames } from '@nmb/ui'

type ChipProps = {
  className?: string;
  label: string;
};

function Chip(props: ChipProps) {
  const { className, label } = props;

  return (
    <div
      className={classnames(`
        bg-sky-900
        border
        border-white
        flex
        align-center 
        px-3
        py-0.5
        rounded-full
        ${className || ''}
      `)}
    >
      {label}
    </div>
  )
}

function CharacterCard(props: GenshinCharacter) {
  const {
    avatar,
    circletStat,
    gobletStat,
    name,
    role,
    sandsStat,
    substats,
    topArtifacts,
    topWeapons,
    vision,
    weaponType,
  } = props;

  return (
    <div>
      <div
        className={classnames(`
          border-t-2
          border-x-2
          border-white
          p-4
          rounded-t-md
        `)}
      >
        <div className="flex flex-row text-black dark:text-white">
          <Image
            className="bg-white border-2 rounded-full"
            src={avatar}
            alt={`${name} Image`}
            priority={true}
            width={100}
            height={100}
          />
          <div className="ml-6">
            <h2 className="mb-4 text-4xl">{name}</h2>
            <div className="flex flex-row">
              <Chip className="mr-2" label={role} />
              <Chip className="mr-2" label={vision} />
              <Chip label={weaponType} />
            </div>
          </div>
        </div>
      </div>
      <div className="bg-slate-800 border-b-2 border-t border-x-2 border-white p-4 rounded-b-md">
        <div className="mb-4 text-black dark:text-white">
          <h3 className="mb-2 text-2xl">Top Weapons</h3>
          <hr className="py-1" />
          <ul className="list-decimal pl-5">
            {topWeapons.map(({ name, rarity }) => (
              <li key={name}>
                {name} [{rarity}⭑]
              </li>
            ))}
          </ul>
        </div>
        <div className="mb-4 text-black dark:text-white">
          <h3 className="mb-2 text-2xl">Top Artifacts</h3>
          <hr className="py-1" />
          <ul className="list-decimal pl-5">
            {topArtifacts.map(({ artifact1, artifact1Count, artifact2, artifact2Count }) => (
              <li key={`${name}-${role}-${artifact1.name}-${artifact1Count}`}>
                {artifact1.name} ({artifact1Count}){artifact2?.name ? `/${artifact2.name} (${artifact2Count})` : ''}
              </li>
            ))}
          </ul>
        </div>
        <div className="mb-4 text-black dark:text-white">
          <h3 className="mb-2 text-2xl">Top Artifact Stats</h3>
          <hr className="py-1" />
          <p className="pb-1">Circlet: {circletStat.join('/')}</p>
          <p className="pb-1">Goblet: {gobletStat.join('/')}</p>
          <p>Sands: {sandsStat.join('/')}</p>
        </div>
        <div className="text-black dark:text-white">
          <h3 className="mb-2 text-2xl">Top Artifact Substats</h3>
          <hr className="py-1" />
          <ul className="list-decimal pl-5">
            {substats.map(substat =>
              <li key={`${name}-${role}-${substat}`}>{substat}</li>
            )}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default CharacterCard;
