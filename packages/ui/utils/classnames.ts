/**
 * Classnames can get long using Tailwind. This makes it so that we can at least
 * make it more visually clear inside a class.
 *
 * @param className
 */
export const classnames = (className: string) => {
  return className.replace(/\s{2,}/g, ' ').trim();
};
