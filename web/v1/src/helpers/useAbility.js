import {
  PureAbility,
  fieldPatternMatcher,
  mongoQueryMatcher,
} from '@casl/ability';

export default function userAbility(permissions) {
  const options = {
    conditionsMatcher: mongoQueryMatcher,
    fieldMatcher: fieldPatternMatcher,
  };
  if (!permissions) {
    return new PureAbility([], options);
  }

  return new PureAbility(permissions, options);
}
