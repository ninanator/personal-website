'use client';

import * as React from 'react';
import { GenshinCharacter } from '../../type';
import CharacterCard from '../CharacterCard';
import { classnames } from '@nmb/ui';

type CharactersProps = {
  data: GenshinCharacter[];
};

function Characters(props: CharactersProps) {
  const { data: allCharacters } = props;

  const allCharactersMap: Record<string, GenshinCharacter> = allCharacters.reduce((acc, curr) => ({
    ...acc,
    [`${curr.name}-${curr.role}`]: curr,
  }), {});
  const allCharacterKeys = Object.keys(allCharactersMap);
  // We want to make everything searchable by arbitrary strings, so push all searchable values into an array
  const allSearchableCharacters: Record<string, string[]> = allCharacters.reduce((searchableCharacters, {
    circletStat,
    gobletStat,
    name,
    role,
    sandsStat,
    topArtifacts,
    topWeapons,
    vision,
    weaponType,
  }) => ({
    ...searchableCharacters,
    [`${name}-${role}`]: [...new Set([
      ...circletStat.flat(),
      ...gobletStat.flat(),
      name,
      role,
      ...sandsStat.flat(),
      ...topArtifacts.flatMap(({ artifact1, artifact2 }) =>
        artifact2 ? [artifact1.name, artifact2.name] : [artifact1.name],
      ),
      ...topWeapons.map(({ name }) => name),
      vision,
      weaponType,
    ])].map(term => term.toLowerCase()),
  }), {});

  const [characters, setCharacters] = React.useState(allCharacters);
  const [showSuboptimal, setShowSuboptimal] = React.useState(true);

  const visibleCharacterCards = characters.filter(({ strongRole }) => showSuboptimal || strongRole);
  const numVisibleCharacterCards = visibleCharacterCards.length;

  return (
    <div className="text-black dark:text-white">
      <div className={classnames(`
        align-center
        flex
        flex-row
        justify-between
        mt-8
        mx-4
      `)}>
        <div className="align-center flex flex-row">
          <input className="w-6 h-6" onChange={event => setShowSuboptimal(!event.target.checked)} type="checkbox" />
          <span className="ml-2">Show Only Recommended Builds</span>
        </div>
        <input
          className="p-2 w-1/2 text-black"
          name="Search"
          onChange={(event) => {
            const searchTerms = event.target.value.toLowerCase().split(' ');
            const numTerms = searchTerms.length;

            if (numTerms <= 1 && searchTerms[0] === '') {
              setCharacters(allCharacters);
            } else {
              const results = allCharacterKeys.filter(searchableCharacterKey => {
                const searchableTerms = allSearchableCharacters[searchableCharacterKey];
                const searchableTermHits = searchableTerms.reduce((totalTermHits, searchableTerm) => {
                  const matches = searchTerms.map(searchTerm => Number(searchableTerm.includes(searchTerm)));

                  return totalTermHits.map((hits, idx) => hits + matches[idx]);
                }, [...Array(numTerms).fill(0)]);

                return searchableTermHits.every(searchableTermHit => searchableTermHit > 0);
              })

              setCharacters(results.map(result => allCharactersMap[result]));
            }
          }}
          placeholder="Search..."
          type="search"
        />
      </div>
      <div className="mb-8 mx-4">
        {numVisibleCharacterCards} Matches
      </div>
      <div className="md:grid md:gap-4 md:grid-cols-3 md:grid-flow-row mt-8 m-3">
        {visibleCharacterCards.map(character => <CharacterCard key={`${character.name}-${character.role}`} {...character} />)}
      </div>
    </div>
  );
}

export default Characters;
