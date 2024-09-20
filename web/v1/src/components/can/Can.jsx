import { Can as OriginalCan } from '@casl/react';
import * as React from 'react';
import { useContext } from 'react';
import { UserContext } from '../../providers/user/UserContextProvider';
import userAbility from '../../helpers/useAbility';

export default function Can(props) {
  const { currentUserState } = useContext(UserContext);

  const ability = userAbility(currentUserState?.permissions);
  return <OriginalCan ability={ability} {...props} />;
}
