import { Skill } from "./skill";
import { UserSkill } from "./user-skill";

export class User {
    id: number;
    email: string;
    username: string;
    password: string;
    enabled: boolean;
    role: string;
    firstName: string;
    lastName: string;
    imageUrl: string;
    bio: string;
    skills: UserSkill [];

    constructor(
        id: number = 0,
        email: string = "",
        username: string = "",
        password: string = "",
        enabled: boolean = false,
        role: string = "",
        firstName: string = "",
        lastName: string = "",
        imageUrl: string = "",
        bio: string = "",
        skills: UserSkill [] = []
    ) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
        this.bio = bio;
        this.skills = skills;
    }



}

