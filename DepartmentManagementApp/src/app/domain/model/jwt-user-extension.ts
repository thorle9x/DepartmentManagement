import {JwtUser} from './jwt-user';

export class JwtUserExtension {
  static isAdmin(user: JwtUser) {
    return user.roles.some(r => r === 'ROLE_ADMIN');
  }

  static isUser(user: JwtUser) {
    return user.roles.some(r => r === 'ROLE_USER');
  }
}
