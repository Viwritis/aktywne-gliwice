import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SportsFacilityService } from 'app/entities/sports-facility/sports-facility.service';
import { ISportsFacility, SportsFacility } from 'app/shared/model/sports-facility.model';
import { SportsFacilityType } from 'app/shared/model/enumerations/sports-facility-type.model';

describe('Service Tests', () => {
  describe('SportsFacility Service', () => {
    let injector: TestBed;
    let service: SportsFacilityService;
    let httpMock: HttpTestingController;
    let elemDefault: ISportsFacility;
    let expectedResult: ISportsFacility | ISportsFacility[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SportsFacilityService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SportsFacility(0, 'AAAAAAA', SportsFacilityType.GYM);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SportsFacility', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SportsFacility()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SportsFacility', () => {
        const returnedFromService = Object.assign(
          {
            housingAssociationName: 'BBBBBB',
            type: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SportsFacility', () => {
        const returnedFromService = Object.assign(
          {
            housingAssociationName: 'BBBBBB',
            type: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SportsFacility', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
