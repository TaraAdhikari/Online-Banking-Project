/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { AppointmentService } from './appointment.service';

describe('AppointmentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AppointmentService]
    });
  });

  it('should ...', inject([AppointmentService], (service: AppointmentService) => {
    expect(service).toBeTruthy();
  }));
});
