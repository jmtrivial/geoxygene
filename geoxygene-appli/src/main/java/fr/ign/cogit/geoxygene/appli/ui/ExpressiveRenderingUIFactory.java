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

package fr.ign.cogit.geoxygene.appli.ui;

import fr.ign.cogit.geoxygene.appli.api.ProjectFrame;
import fr.ign.cogit.geoxygene.style.expressive.ExpressiveRenderingDescriptor;

/**
 * @author JeT
 * 
 */
public class ExpressiveRenderingUIFactory {

    /**
     * private constructor
     */
    private ExpressiveRenderingUIFactory() {
        // private constructor
    }

    /**
     * Create an expressiveRendering UI
     * 
     * @param descriptor
     * @return
     */
    public static GenericParameterUI getExpressiveRenderingUI(
            ExpressiveRenderingDescriptor descriptor, ProjectFrame projectFrame) {
        if (descriptor == null) {
            return null;
        }
        //Temporarily disabled
//        if (descriptor instanceof ExpressiveDescriptorImpl) {
//            return getStrokeExpressiveRenderingUI(
//                    (ExpressiveDescriptorImpl) descriptor,
//                    projectFrame);
//        }
//        if (descriptor instanceof ExpressiveDescriptorImpl) {
//            return getStrokeExpressiveRenderingUI(
//                    (ExpressiveDescriptorImpl) descriptor,
//                    projectFrame);
//        }
        return new NoExpressiveRenderingUI(descriptor, projectFrame);
    }

    //Temporarily disabled
    
    //
//    /**
//     * Create an expressiveRendering UI for Strokes (1D)
//     * 
//     * @param descriptor
//     * @return
//     */
//    public static GenericParameterUI getStrokeExpressiveRenderingUI(
//            StrokeExpressiveRenderingDescriptor descriptor,
//            ProjectFrame projectFrame) {
//        if (descriptor instanceof StrokeTextureExpressiveRenderingDescriptor) {
//            return getExpressiveRenderingUI(
//                    (StrokeTextureExpressiveRenderingDescriptor) descriptor,
//                    projectFrame);
//        } else if (descriptor instanceof BasicTextureExpressiveRenderingDescriptor) {
//            return getExpressiveRenderingUI(
//                    (BasicTextureExpressiveRenderingDescriptor) descriptor,
//                    projectFrame);
//        }
//        return new NoExpressiveRenderingUI(descriptor, projectFrame);
//    }
//
//    /**
//     * Create a stroke texture Rendering UI
//     * 
//     * @param descriptor
//     * @return
//     */
//    private static StrokeTextureExpressiveRenderingUI getExpressiveRenderingUI(
//            final StrokeTextureExpressiveRenderingDescriptor descriptor,
//            ProjectFrame projectFrame) {
//        return new StrokeTextureExpressiveRenderingUI(descriptor, projectFrame);
//    }
//
//    /**
//     * Create a basic texture Rendering UI
//     * 
//     * @param descriptor
//     * @return
//     */
//    private static BasicTextureExpressiveRenderingUI getExpressiveRenderingUI(
//            final BasicTextureExpressiveRenderingDescriptor descriptor,
//            ProjectFrame projectFrame) {
//        return new BasicTextureExpressiveRenderingUI(descriptor, projectFrame);
//    }
//
//    /**
//     * Create an expressiveRendering UI for Fill (2D)
//     * 
//     * @param descriptor
//     * @return
//     */
//    public static GenericParameterUI getFillExpressiveRenderingUI(
//            Fill2DDescriptor descriptor, ProjectFrame projectFrame) {
//        if (descriptor instanceof GradientSubshaderDescriptor) {
//            GradientSubshaderDescriptor gradientDescriptor = (GradientSubshaderDescriptor) descriptor;
//            return getExpressiveRenderingUI(gradientDescriptor, projectFrame);
//        } else if (descriptor instanceof BinaryGradientImageDescriptor) {
//            return getExpressiveRenderingUI(
//                    (BinaryGradientImageDescriptor) descriptor, projectFrame);
//        }
//
//        return new NoExpressiveRenderingUI(descriptor, projectFrame);
//    }
//
//    /**
//     * Create a stroke texture Rendering UI
//     * 
//     * @param descriptor
//     * @return
//     */
//    private static BinaryGradientImageUI getExpressiveRenderingUI(
//            final BinaryGradientImageDescriptor descriptor,
//            ProjectFrame projectFrame) {
//        return new BinaryGradientImageUI(descriptor, projectFrame);
//    }
//
//    /**
//     * Create a gradient texture Rendering UI
//     * 
//     * @param descriptor
//     * @return
//     */
//    private static GradientExpressiveRenderingUI getExpressiveRenderingUI(
//            final GradientSubshaderDescriptor descriptor,
//            ProjectFrame projectFrame) {
//        return new GradientExpressiveRenderingUI(descriptor, projectFrame);
//    }

}
