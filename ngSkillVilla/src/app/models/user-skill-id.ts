import { User } from "./user";

export class UserSkillId {

    userId: number;
    skillId: number;

    constructor(
        userId: number = 0,
        skillId: number = 0
    ){
        this.userId = userId;
        this.skillId = skillId;
    }

}
