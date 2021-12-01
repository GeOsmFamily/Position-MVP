import { Feature } from 'src/app/modules/ol';
import { Injectable } from '@angular/core';
import { DeviceDetectorService } from 'ngx-device-detector';
import VectorLayer from 'ol/layer/Vector';
import { MapHelper } from '../helpers/mapHelper';

@Injectable({
  providedIn: 'root'
})
export class DeviceDetectionService {
   mapHelper = new MapHelper();
   features:any
  deviceInfo = '';
  isDesktopDevice=false
  isTablet=false
  isMobile=false
  constructor(private deviceDetection:DeviceDetectorService) {
    this.epicFunction()
    if (this.mapHelper.getLayerByName('searchResultLayer').length > 0) {
      var searchResultLayer = new VectorLayer();
      searchResultLayer = this.mapHelper.getLayerByName('searchResultLayer')[0];
      var source = searchResultLayer.getSource();
    this.features = source.getFeatures();
    console.log("feature")
    console.log(this.features!.get('description'))
    }
  }

  epicFunction() {

    this.isMobile = this.deviceDetection.isMobile();
    this.isTablet = this.deviceDetection.isTablet();

    this.isDesktopDevice = this.deviceDetection.isDesktop();
  }
}
