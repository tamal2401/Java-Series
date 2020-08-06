export class User{
    public userName : string;
    public password : string;
    public email?: string;
    constructor(name, pwd){
        this.userName = name;
        this.password = pwd;
    }
}
