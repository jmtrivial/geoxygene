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

package fr.ign.cogit.geoxygene.appli.gl;

import fr.ign.cogit.geoxygene.util.gl.GLException;
import fr.ign.cogit.geoxygene.util.gl.GLProgram;

/**
 * @author JeT
 * 
 */
public interface Subshader {

    /**
     * declare the uniforms variables used in this shader
     * 
     * @param program
     * @throws GLException
     */
    public void declareUniforms(GLProgram program) throws GLException;

    /**
     * Initialize the shader before rendering (set uniforms)
     * 
     * @throws GLException
     */
    public void setUniforms(GLProgram program) throws GLException;

    /**
     * configure the shader in the given program. Set fragment, shaders used by
     * program. This method must be called before using
     * GLProgram::getProgramId() method which generates the program (compile,
     * link)
     * 
     * @param program
     * @throws GLException
     */
    public void configureProgram(GLProgram program) throws GLException;

}
