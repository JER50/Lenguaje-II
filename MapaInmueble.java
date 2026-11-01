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
import java.awt.BorderLayout;
import java.util.Set;

public class MapaInmueble {

    public static void agregarMapa(JPanel panel, double latitud, double longitud) {
        JXMapViewer mapa = new JXMapViewer();
        mapa.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo("OpenStreetMap", "https://tile.openstreetmap.org")));

        GeoPosition posicion = new GeoPosition(latitud, longitud);
        mapa.setZoom(3);
        mapa.setAddressLocation(posicion);

        // Marcador
        WaypointPainter<Waypoint> pintor = new WaypointPainter<>();
        pintor.setWaypoints(Set.of(new DefaultWaypoint(posicion)));
        mapa.setOverlayPainter(pintor);

        // Mover con el mouse y zoom con la rueda
        PanMouseInputListener pan = new PanMouseInputListener(mapa);
        mapa.addMouseListener(pan);
        mapa.addMouseMotionListener(pan);
        mapa.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapa));

        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.add(mapa, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
}