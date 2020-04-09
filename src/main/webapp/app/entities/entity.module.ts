import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'sports-facility',
        loadChildren: () => import('./sports-facility/sports-facility.module').then(m => m.AktywneGliwiceSportsFacilityModule)
      },
      {
        path: 'memebers',
        loadChildren: () => import('./memebers/memebers.module').then(m => m.AktywneGliwiceMemebersModule)
      },
      {
        path: 'extandad-user',
        loadChildren: () => import('./extandad-user/extandad-user.module').then(m => m.AktywneGliwiceExtandadUserModule)
      },
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.AktywneGliwiceRegionModule)
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.AktywneGliwiceCountryModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.AktywneGliwiceLocationModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class AktywneGliwiceEntityModule {}
