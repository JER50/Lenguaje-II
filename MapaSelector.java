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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.function.BiConsumer;

public class MapaSelector {

    public static void agregarMapa(JPanel panel, double latInicial, double lonInicial, BiConsumer<Double, Double> alSeleccionar) {
        JXMapViewer mapa = new JXMapViewer();
        mapa.setTileFactory(new DefaultTileFactory(
                new OSMTileFactoryInfo("OpenStreetMap", "https://tile.openstreetmap.org")));
        mapa.setZoom(6);
        mapa.setAddressLocation(new GeoPosition(latInicial, lonInicial));

        WaypointPainter<Waypoint> pintor = new WaypointPainter<>();
        mapa.setOverlayPainter(pintor);

        // Mover con el mouse y zoom con la rueda
        PanMouseInputListener pan = new PanMouseInputListener(mapa);
        mapa.addMouseListener(pan);
        mapa.addMouseMotionListener(pan);
        mapa.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapa));

        // Clic izquierdo: obtener coordenadas y poner marcador
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

        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.add(mapa, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
}