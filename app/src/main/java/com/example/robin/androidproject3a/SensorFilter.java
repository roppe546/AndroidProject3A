package com.example.robin.androidproject3a;

/**
 * This class represents a filter used to smooth out data sampled from a sensor.
 *
 * Created by robin on 8/12/15.
 */
public class SensorFilter {
    private float currentFilteredVal;
    private float previousFilteredVal;
    private float filterFactor;

    /**
     * Constructor. Create a filter.
     *
     * @param filterFactor  the factor used for filtering
     * @param startVal      the start value of the filter value
     */
    public SensorFilter(float filterFactor, float startVal) {
        this.currentFilteredVal = filterFactor;
        this.previousFilteredVal = startVal;
        this.filterFactor = startVal;
    }

    /**
     * Filter data by using filter factor and the previous calculation.
     * Return the new filtered value.
     *
     * @param n     the value to filter
     * @return      the filtered value
     */
    public float filter(float n) {
        currentFilteredVal = filterFactor * previousFilteredVal + (1 - filterFactor) * n;
        previousFilteredVal = currentFilteredVal;
        return currentFilteredVal;
    }

    public float getCurrentFilteredVal() {
        return currentFilteredVal;
    }

    public void setCurrentFilteredVal(float currentFilteredVal) {
        this.currentFilteredVal = currentFilteredVal;
    }

    public float getPreviousFilteredVal() {
        return previousFilteredVal;
    }

    public void setPreviousFilteredVal(float previousFilteredVal) {
        this.previousFilteredVal = previousFilteredVal;
    }

    public float getFilterFactor() {
        return filterFactor;
    }

    public void setFilterFactor(float filterFactor) {
        this.filterFactor = filterFactor;
    }
}