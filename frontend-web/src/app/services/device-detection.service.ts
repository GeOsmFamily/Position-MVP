import { Injectable } from '@angular/core';
import { DeviceDetectorService } from 'ngx-device-detector';

@Injectable({
  providedIn: 'root'
})
export class DeviceDetectionService {

  deviceInfo = '';
  isDesktopDevice=false
  isTablet=false
  isMobile=false
  constructor(private deviceDetection:DeviceDetectorService) {
    this.epicFunction()
  }

  epicFunction() {

    this.isMobile = this.deviceDetection.isMobile();
    this.isTablet = this.deviceDetection.isTablet();

    this.isDesktopDevice = this.deviceDetection.isDesktop();
  }
}
