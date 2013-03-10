/*
 * Copyright 2013 David Jurgens
 *
 * This file is part of the Cluster-Comparison package and is covered under the
 * terms and conditions therein.
 *
 * The Cluster-Comparison package is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version 2
 * as published by the Free Software Foundation and distributed hereunder to
 * you.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND NO REPRESENTATIONS OR WARRANTIES,
 * EXPRESS OR IMPLIED ARE MADE.  BY WAY OF EXAMPLE, BUT NOT LIMITATION, WE MAKE
 * NO REPRESENTATIONS OR WARRANTIES OF MERCHANT- ABILITY OR FITNESS FOR ANY
 * PARTICULAR PURPOSE OR THAT THE USE OF THE LICENSED SOFTWARE OR DOCUMENTATION
 * WILL NOT INFRINGE ANY THIRD PARTY PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER
 * RIGHTS.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package edu.ucla.clustercomparison.cl;

import edu.ucla.clustercomparison.BaseScorer;
import edu.ucla.clustercomparison.Evaluation;
import edu.ucla.clustercomparison.GoodmanKruskalGamma;

import java.io.*;

import java.util.*;


public class GammaScorer extends CliRunner {

    @Override protected Evaluation getEvaluation() {
        return new GoodmanKruskalGamma();
    }

    @Override protected String getEvalName() {
        return "Goodman-Kruskal Gamma";
    }

    public static void main(String[] args) throws Exception {   

        if (args.length < 2) {
            System.out.println(
                "usage: java -jar perception.jar gold.key " +
                "to-test.key [remapped.key]\n\n" +
                "The last argument specifies an optional output file that\n"+
                "contains the labeling of the to-test.key after the sense-remapping has\n"+
                "been performed.");
            return;     
        }

        WeightedTauScorer rs = new WeightedTauScorer();
        boolean performRemapping = !args[0].equals("--no-remapping");
        int start = (!performRemapping) ? 1 : 0;

        rs.score(new File(args[start]), new File(args[start+1]), 
                 (args.length > start+2) ? new File(args[start+2]) : null,
                 performRemapping);
    }
}
