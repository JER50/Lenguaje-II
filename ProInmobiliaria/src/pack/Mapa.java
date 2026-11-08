package pack;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.function.BiConsumer;

public class Mapa {

    private final JXMapViewer mapa;
    private final WaypointPainter<Waypoint> pintor;

    //marcador fijo
    public Mapa(JPanel panel, double latitud, double longitud) {
        this(panel, latitud, longitud, 3);
        GeoPosition posicion = new GeoPosition(latitud, longitud);
        pintor.setWaypoints(Set.of(new DefaultWaypoint(posicion)));
    }

    //Coloca marcador, devuelve coordenadas
    public Mapa(JPanel panel, double latInicial, double lonInicial, BiConsumer<Double, Double> alSeleccionar) {
        this(panel, latInicial, lonInicial, 6);

        mapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    GeoPosition pos = mapa.convertPointToGeoPosition(e.getPoint());
                    pintor.setWaypoints(Set.of(new DefaultWaypoint(pos)));
                    mapa.repaint();
                    alSeleccionar.accept(pos.getLatitude(), pos.getLongitude());
                }
            }
        });
    }

    //Configuración en común
    private Mapa(JPanel panel, double lat, double lon, int zoom) {
        mapa = new JXMapViewer();
        mapa.setTileFactory(new DefaultTileFactory(
                new OSMTileFactoryInfo("OpenStreetMap", "https://tile.openstreetmap.org")));
        mapa.setZoom(zoom);
        mapa.setAddressLocation(new GeoPosition(lat, lon));

        pintor = new WaypointPainter<>();
        mapa.setOverlayPainter(pintor);

        PanMouseInputListener pan = new PanMouseInputListener(mapa);
        mapa.addMouseListener(pan);
        mapa.addMouseMotionListener(pan);
        mapa.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapa));

        panel.removeAll();
        panel.add(mapa);
        panel.revalidate();
        panel.repaint();
    }
}