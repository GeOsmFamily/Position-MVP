package com.sogefi.position.utils;

import static com.mapbox.mapboxsdk.style.expressions.Expression.accumulated;
import static com.mapbox.mapboxsdk.style.expressions.Expression.concat;
import static com.mapbox.mapboxsdk.style.expressions.Expression.division;
import static com.mapbox.mapboxsdk.style.expressions.Expression.exponential;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.has;
import static com.mapbox.mapboxsdk.style.expressions.Expression.interpolate;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.expressions.Expression.match;
import static com.mapbox.mapboxsdk.style.expressions.Expression.max;
import static com.mapbox.mapboxsdk.style.expressions.Expression.neq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.rgb;
import static com.mapbox.mapboxsdk.style.expressions.Expression.stop;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconSize;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineCap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineGradient;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineJoin;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineWidth;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textField;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textSize;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.UiSettings;
import com.mapbox.mapboxsdk.style.expressions.Expression;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonOptions;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.utils.BitmapUtils;
import com.sogefi.position.R;

public class MapBoxUtils {

    MapboxMap mapboxMap;
    double zoomStep = 0;

    private static final String ORIGIN_ICON_ID = "origin-icon-id";
    private static final String DESTINATION_ICON_ID = "destination-icon-id";
    private static final String ROUTE_LAYER_ID = "route-layer-id";
    private static final String ROUTE_LINE_SOURCE_ID = "route-source-id";
    private static final String ICON_LAYER_ID = "icon-layer-id";
    private static final String ICON_SOURCE_ID = "icon-source-id";
    private static final String GEOJSON_SOURCE_ID = "geojson-source-id";
    private static final String UNCLUSTERED_POINTS = "unclustered-points";

    public MapBoxUtils(MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
    }

    //Methode pour incrementer/decrementer le zoom
    public void incrZoom(Double step) {
        CameraPosition cam = mapboxMap.getCameraPosition();
        zoomStep = cam.zoom + step;
        zoom(zoomStep, cam.target);
    }

    //Methode pour gerer le zoom
    public void zoom(Double zoom, LatLng latLng) {
        mapboxMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(
                        new CameraPosition.Builder()
                                .zoom(zoom)
                                .target(new LatLng(latLng))
                                .build()

                )
        );

    }

    public void setMapSetting() {
        UiSettings uiSettings = mapboxMap.getUiSettings();
        uiSettings.setCompassMargins(0, 150, 6, 0);
        uiSettings.setLogoEnabled(false);
        uiSettings.setAttributionEnabled(false);
    }

    //Initialiser les sources pour tracer une route
    public void initSources(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addSource(new GeoJsonSource(ROUTE_LINE_SOURCE_ID, new GeoJsonOptions().withLineMetrics(true)));
      /*  loadedMapStyle.addSource(new GeoJsonSource(GEOJSON_SOURCE_ID, new GeoJsonOptions()
                .withCluster(true)
                .withClusterMaxZoom(14)
                .withClusterRadius(10)
                .withClusterProperty("max", max(accumulated(), get("max")), get("mag"))
                .withClusterProperty("sum", literal("+"), get("mag"))
                .withClusterProperty("felt", literal("any"), neq(get("felt"), literal("null")))
        ));*/
    }

    //Initialiser les layers pour tracer une route
    public void initLayers(@NonNull Style loadedMapStyle) {

        loadedMapStyle.addLayer(new LineLayer(ROUTE_LAYER_ID, ROUTE_LINE_SOURCE_ID).withProperties(
                lineCap(Property.LINE_CAP_ROUND),
                lineJoin(Property.LINE_JOIN_ROUND),
                lineWidth(5f),
                lineGradient(
                        Expression.interpolate(
                                Expression.linear(), Expression.lineProgress(),
                                stop(0f, Expression.rgb(4, 188, 148)),
                             //   stop(0.6f, Expression.rgb(255, 0, 0)),
                                stop(0.8f, Expression.rgb(252, 204, 68))
                        )
                )
        ));

        loadedMapStyle.addLayer(new SymbolLayer(ICON_LAYER_ID, ICON_SOURCE_ID).withProperties(
                iconImage(match(get("originDestination"), literal("origin"),
                        stop("origin", ORIGIN_ICON_ID),
                        stop("destination", DESTINATION_ICON_ID))),
                iconIgnorePlacement(true),
                iconAllowOverlap(true),
                iconOffset(new Float[]{0f, -4f})));

      /* loadedMapStyle.addLayer( new SymbolLayer("unclustered-points", GEOJSON_SOURCE_ID)
                .withProperties(
                        iconImage("markerBatimentImage"),
                        iconSize(
                                division(
                                        get("mag"), literal(4.0f)
                                )
                        ),
                        iconColor(
                                interpolate(exponential(1), get("mag"),
                                        stop(2.0, rgb(0, 255, 0)),
                                        stop(4.5, rgb(0, 0, 255)),
                                        stop(7.0, rgb(255, 0, 0))
                                )
                        )
                )
                .withFilter(has("mag")));

        loadedMapStyle.addLayer(new SymbolLayer("property", GEOJSON_SOURCE_ID)
                .withProperties(
                        textField(concat(get("point_count"), literal(", "), get("max"))),
                        textSize(12f),
                        textColor(Color.WHITE),
                        textIgnorePlacement(true),
                        textAllowOverlap(true)
                ));*/

    }

    //creer les icones de depart et d'arrivée
    public FeatureCollection getOriginAndDestinationFeatureCollection(Point origin, Point destination) {
        Feature originFeature = Feature.fromGeometry(origin);
        originFeature.addStringProperty("originDestination", "origin");
        Feature destinationFeature = Feature.fromGeometry(destination);
        destinationFeature.addStringProperty("originDestination", "destination");
        return FeatureCollection.fromFeatures(new Feature[]{originFeature, destinationFeature});
    }


}
