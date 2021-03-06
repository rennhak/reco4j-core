/*
 * JaccardSimilarity.java
 * 
 * Copyright (C) 2012 Alessandro Negro <alessandro.negro at reco4j.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.reco4j.graph.similarity;

import java.util.List;
import org.reco4j.graph.IEdge;
import org.reco4j.graph.IEdgeType;
import org.reco4j.graph.IGraph;
import org.reco4j.graph.INode;

/**
 *
 * @author ale
 */
public class JaccardSimilarity implements ISimilarity
{
  private static JaccardSimilarity theInstance = new JaccardSimilarity();
  private JaccardSimilarity()
  {
    
  }
  
  public static JaccardSimilarity getInstance()
  {
    return theInstance;
  }
  @Override
  public double getSimilarity(INode x, INode y, IEdgeType edgeType, IGraph dataSet)
  {
    int commonUsers = 0;
    List<IEdge> xInEdge;
    xInEdge = x.getInEdge(edgeType);
    //TODO: Improve using the graph capabilities
    for (IEdge edge : xInEdge)
    {
      if (edge.getDestination().isConnected(y, edgeType))
        commonUsers++;
    }
    
    int totalUsers = xInEdge.size() + y.getInEdgeNumber(edgeType) - commonUsers;
    double sim = 0.0;
    if (commonUsers > 0)
      sim = (double) commonUsers / (double) totalUsers;
    return sim;
  }
}
