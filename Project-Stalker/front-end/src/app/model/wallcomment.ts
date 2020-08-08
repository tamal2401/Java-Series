export class WallComment {
  private userName: string;
  private time: Date;
  private messege: string;

  constructor(user, time, msg) {
    this.userName = user;
    this.time = time;
    this.messege = msg;
  }

  public get getUserName() {
    return this.userName;
  }

}
