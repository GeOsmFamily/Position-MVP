import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FicheEntrepriseComponent } from './fiche-entreprise.component';

describe('FicheEntrepriseComponent', () => {
  let component: FicheEntrepriseComponent;
  let fixture: ComponentFixture<FicheEntrepriseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FicheEntrepriseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FicheEntrepriseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
