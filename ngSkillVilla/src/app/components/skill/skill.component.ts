import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SkillService } from '../../services/skill.service';
import { Skill } from '../../models/skill';

@Component({
  selector: 'app-skill',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './skill.component.html',
  styleUrl: './skill.component.css'
})
export class SkillComponent {

  //FIELDS------------------------------------------------------------
  skills: Skill[] = [];
  newSkill: Skill = new Skill();
  selected: Skill | null = null;
  editSkill: Skill | null = null;

  //CONSTRUCTOR-------------------------------------------------------
  constructor(private router: Router, private activatedRoute: ActivatedRoute, private skillService: SkillService){}

//---------------------------------------------------------------------

ngOnInit(): void {
  this.activatedRoute.paramMap.subscribe(
    {
      next: (params) => {
        console.log(params.get("skillId"))
        let skillIdStr = params.get("skillId");
        if (skillIdStr) {
          let skillId = parseInt(skillIdStr);
          if (isNaN(skillId)) {
            this.router.navigateByUrl("invalid");
          } else (
            console.log(this.selected)
            
            
          )
        }
      }
    });
}

  //METHODS-----------------------------------------------------------

  displaySkill(skillPage: Skill) {
    this.selected = skillPage;
    let url: string = '[skill]/' + skillPage.id
    this.router.navigateByUrl(url);
  }

  reload(){
    this.skillService.index().subscribe({
      next: (dbSkillVilla: Skill[]) => {
        console.log(dbSkillVilla)
        this.skills = dbSkillVilla
      },
      error: (err) => {
        console.log("something went wrong with reload()")
      }
    })
  }

  addSkill(skill: Skill){
    this.skillService.create(skill).subscribe({
      next: (skill) => {
        this.reload();
        this.newSkill = new Skill();
      },
      error: (err) => {
        console.log("something went wrong adding skill")}
    })
  }

  updateSkill(skill : Skill) {
    this.skillService.update(skill, skill.id).subscribe({
      next: (skill) => {
        this.reload();
        this.selected = null;
        this.editSkill = null;
      },
      error: (err) => {
        console.log("something went wrong updating skill")}
    });
  }

  setUpdatedSkill() {
    this.editSkill = Object.assign({}, this.selected);
  }

  delete(id: number) {
    this.skillService.destroy(id).subscribe({
      next: () => {
        this.reload();
      },
      error: () => {}
    });
  }

}
