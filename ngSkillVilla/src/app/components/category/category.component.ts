import { Skill } from './../../models/skill';
import { SkillService } from './../../services/skill.service';
import { Component } from '@angular/core';
import { Category } from '../../models/category';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../../services/category.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SkillComponent } from '../skill/skill.component';

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [CommonModule, FormsModule, SkillComponent],
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent {

  categories: Category[] = [];
  newCategory: Category = new Category();
  selected: Category | null = null;
  editCategory: Category | null = null;
  skills: Skill[] = [];
  //---------------------------------------------------------------------

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private categoryService : CategoryService,
    private skillService : SkillService
  ) {

  }

  //---------------------------------------------------------------------

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      {
        next: (params) => {
          console.log(params.get("categoryId"))
          let categoryIdStr = params.get("categoryId");
          if (categoryIdStr) {
            let categoryId = parseInt(categoryIdStr);
            if (isNaN(categoryId)) {
              this.router.navigateByUrl("invalid");
            } else (
              console.log(this.selected)
              
              
            )
          }
        }
      });
  }

  //---------------------------------------------------------------------


  displaySinglePost(categoryPage: Category) {
    this.selected = categoryPage;
    let url: string = '[category]/' + categoryPage.id
    this.router.navigateByUrl(url);
  }

  displayAllCategories() {
    this.categoryService.index().subscribe({
      next: (dbSkillVilla: Category[]) => {
        console.log(dbSkillVilla)
        this.categories = dbSkillVilla
      },
      error: (err) => {
        console.log("something went wrong loading categories")
      }
    })
  }

  reload() {
    this.categoryService.index().subscribe({
      next: (dbSkillVilla: Category[]) => {
        console.log(dbSkillVilla)
        this.categories = dbSkillVilla
      },
      error: (err) => {
        console.log("something went wrong with reload()")
      }
    })
  }

  addCategory(category : Category){
    this.categoryService.create(category).subscribe({
      next: (category) => {
        this.reload();
        this.newCategory = new Category();
      }
    })
  }
  
  updateCategory(category : Category){
    this.categoryService.update(category, category.id).subscribe({
      next: (category) => {
        this.reload();
        this.selected = null;
        this.editCategory = null;
      },
      error: () => {}
    });
  }

  setUpdatedCategory() {
    this.editCategory = Object.assign({}, this.selected);
  }

  deleteCategory(id: number) {
    this.categoryService.destroy(id).subscribe({
      next: () => {
      this.reload();
      },
      error: () => {}
    });
  }


}
