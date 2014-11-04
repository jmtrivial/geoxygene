/*******************************************************************************
 * This file is part of the GeOxygene project source files.
 * 
 * GeOxygene aims at providing an open framework which implements OGC/ISO
 * specifications for the development and deployment of geographic (GIS)
 * applications. It is a open source contribution of the COGIT laboratory at the
 * Institut Géographique National (the French National Mapping Agency).
 * 
 * See: http://oxygene-project.sourceforge.net
 * 
 * Copyright (C) 2005 Institut Géographique National
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library (see file LICENSE if present); if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA
 *******************************************************************************/

package fr.ign.cogit.geoxygene.appli.render.primitive;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fr.ign.cogit.geoxygene.api.feature.IFeature;
import fr.ign.cogit.geoxygene.api.spatial.coordgeom.IEnvelope;
import fr.ign.cogit.geoxygene.api.spatial.geomroot.IGeometry;
import fr.ign.cogit.geoxygene.appli.Viewport;
import fr.ign.cogit.geoxygene.appli.gl.GLComplexFactory;
import fr.ign.cogit.geoxygene.appli.gl.GLSimpleComplex;
import fr.ign.cogit.geoxygene.appli.gl.GLTextComplex;
import fr.ign.cogit.geoxygene.appli.render.GeoxComplexRenderer;
import fr.ign.cogit.geoxygene.appli.render.GeoxRendererManager;
import fr.ign.cogit.geoxygene.appli.render.LwjglLayerRenderer;
import fr.ign.cogit.geoxygene.appli.task.TaskState;
import fr.ign.cogit.geoxygene.style.Mark;
import fr.ign.cogit.geoxygene.style.PointSymbolizer;
import fr.ign.cogit.geoxygene.style.Symbolizer;
import fr.ign.cogit.geoxygene.style.TextSymbolizer;
import fr.ign.cogit.geoxygene.util.gl.GLComplex;
import fr.ign.cogit.geoxygene.util.gl.GLComplexRenderer;

/**
 * @author JeT
 * 
 */
public class DisplayablePoint extends AbstractDisplayable {

    private static final Logger logger = Logger
            .getLogger(DisplayablePoint.class.getName()); // logger
    private static final Colorizer partialColorizer = new SolidColorizer(
            Color.green);
    private final List<IGeometry> geometries = new ArrayList<IGeometry>();

    /**
     * Constructor
     * 
     * @param name
     * @param viewport
     * @param multiPoints
     * @param symbolizer
     */
    public DisplayablePoint(String name, Viewport viewport, IGeometry geometry,
            IFeature feature, Symbolizer symbolizer,
            LwjglLayerRenderer layerRenderer, GLComplexRenderer partialRenderer) {
        super(name, viewport, layerRenderer, feature, symbolizer);
        this.addGeometry(geometry);
        this.generatePartialRepresentation(partialRenderer);
    }

    private final void generatePartialRepresentation(
            GLComplexRenderer partialRenderer) {
        IEnvelope envelope = IGeometryUtil.getEnvelope(this.geometries);
        double minX = envelope.minX();
        double minY = envelope.minY();
        this.setPartialRepresentation(GLComplexFactory.createQuickPoints(
                this.getName() + "-partial", this.geometries, partialColorizer,
                null, minX, minY, partialRenderer));
    }

    /**
     * @param point
     */
    public final void addGeometry(IGeometry geometry) {
        this.geometries.add(geometry);
    }

    @Override
    public boolean isProgressable() {
        return false;
    }

    @Override
    public boolean isPausable() {
        return false;
    }

    @Override
    public boolean isStoppable() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public List<GLComplex> generateFullRepresentation() {
        List<GLComplex> complexes = new ArrayList<GLComplex>();

        if (this.getSymbolizer().isPointSymbolizer()) {
            PointSymbolizer pointSymbolizer = (PointSymbolizer) this
                    .getSymbolizer();
            complexes.addAll(this.generateWithPointSymbolizer(pointSymbolizer));
            return complexes;
        } else if (this.getSymbolizer().isTextSymbolizer()) {
            TextSymbolizer symbolizer = (TextSymbolizer) this.getSymbolizer();
            GLTextComplex primitive = new GLTextComplex("toponym-"
                    + this.getName(), this.getFeature(), 0, 0);
            primitive.setRenderer(GeoxRendererManager.getOrCreateTextRenderer(
                    symbolizer, this.getLayerRenderer()));
            complexes.add(primitive);
            return complexes;
        }
        logger.error("Do not handle points with "
                + this.getSymbolizer().getClass().getSimpleName()
                + " symbolizer");
        super.setError(new UnsupportedOperationException(
                "Do not handle points with "
                        + this.getSymbolizer().getClass().getSimpleName()
                        + " symbolizer"));
        super.setState(TaskState.ERROR);
        return null;
    }

    private List<GLComplex> generateWithPointSymbolizer(
            PointSymbolizer symbolizer) {
        List<GLComplex> complexes = new ArrayList<GLComplex>();
        IEnvelope envelope = IGeometryUtil.getEnvelope(this.geometries);
        double minX = envelope.minX();
        double minY = envelope.minY();

        for (Mark mark : symbolizer.getGraphic().getMarks()) {
            Shape markShape = mark.toShape();
            float size = symbolizer.getGraphic().getSize();
            // create the shape fo the mark. daptive transform will be done for
            // each point (location, size, etc...)
            AffineTransform at = new AffineTransform();
            at.scale(size, size);
            markShape = at.createTransformedShape(markShape);

            for (IGeometry geometry : this.geometries) {
                AffineTransform atGeometry = new AffineTransform();
                atGeometry.translate(geometry.centroid().getX(), geometry
                        .centroid().getY());
                Shape markShapeGeometry = atGeometry
                        .createTransformedShape(markShape);
                // TODO: add scale (viewport depend) and rotation
                GeoxComplexRenderer renderer = GeoxRendererManager
                        .getOrCreateSurfaceRenderer(symbolizer,
                                this.getLayerRenderer());
                GLSimpleComplex markFillComplex = GLComplexFactory.toGLComplex(
                        this.getName() + "-mark-filled", markShapeGeometry,
                        minX, minY, renderer);
                markFillComplex.setColor(mark.getFill().getColor());
                complexes.add(markFillComplex);

                if (mark.getStroke() != null) {
                    // TODO: add scale (viewport depend) and rotation
                    renderer = GeoxRendererManager.getOrCreateLineRenderer(
                            symbolizer, this.getLayerRenderer());
                    GLSimpleComplex markOutlineComplex = GLComplexFactory
                            .createShapeOutline(this.getName()
                                    + "-mark-outline", markShapeGeometry,
                                    mark.getStroke(), minX, minY, renderer);
                    markOutlineComplex.setColor(mark.getStroke().getColor());
                    complexes.add(markOutlineComplex);
                } else {
                    logger.warn("Mark point has a null stroke. They won't be renderered until a default stroke is set");
                }
            }

        }
        return complexes;
    }

}
