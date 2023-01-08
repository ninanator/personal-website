import 'server-only';
import { getCharacters } from '../../src/server/contentful';
import Characters from '../../src/components/Characters';

export default async function Page() {
  const characterData = getCharacters();

  const [characters] = await Promise.all([characterData]);

  return <Characters data={characters} />;
}
