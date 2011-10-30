/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * 
 * @author Yilei Qian
 */
public class GBC extends GridBagConstraints {

	/**
	 * 
	 * @param gridx
	 * @param gridy
	 */
	public GBC(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}

	/**
	 * 
	 * @param gridx
	 * @param gridy
	 * @param gridwidth
	 * @param gridheight
	 */
	public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}

	/**
	 * 
	 * @param anchor
	 * @return
	 */
	public GBC setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}

	/**
	 * 
	 * @param fill
	 * @return
	 */
	public GBC setFill(int fill) {
		this.fill = fill;
		return this;
	}

	/**
	 * 
	 * @param weightx
	 * @param weighty
	 * @return
	 */
	public GBC setWeight(double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}

	/**
	 * 
	 * @param distance
	 * @return
	 */
	public GBC setInset(int distance) {
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}

	/**
	 * 
	 * @param top
	 * @param left
	 * @param bottom
	 * @param right
	 * @return
	 */
	public GBC setInsets(int top, int left, int bottom, int right) {
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}

	/**
	 * 
	 * @param ipadx
	 * @param ipady
	 * @return
	 */
	public GBC setIpad(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
}