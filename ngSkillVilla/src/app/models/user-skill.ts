import { Skill } from "./skill";
import { UserSkillId } from "./user-skill-id";

export class UserSkill {

    id: UserSkillId | null;
    level: string;
    skill: Skill | null;

    constructor(
        id: UserSkillId | null = null,
       level: string = "",
       skill: Skill | null = null

    ){
        this.id = id;
        this.level = level;
        this.skill = skill;
    }

}
