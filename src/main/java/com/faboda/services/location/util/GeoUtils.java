package com.faboda.services.location.util;

import com.faboda.services.location.exceptions.InvalidArgumentException;
import com.faboda.services.location.models.fields.Point;

public class GeoUtils {

    /**
     * Check if a point is within a specified radius of another point.
     *
     * @param point1   The first point (e.g., from the database).
     * @param point2   The second point to be checked.
     * @param radius   The radius in kilometers.
     * @return True if point2 is within the specified radius of point1, false otherwise.
     */
    public static boolean isPointWithinRadius(Point point1, Point point2, double radius) {
        if (point1 == null || point2 == null || radius <= 0) {
            throw new InvalidArgumentException();
        }

        double distance = calculateDistance(point1, point2);

        return distance <= radius;
    }

    /**
     * Calculate the great-circle distance between two points using Haversine formula.
     *
     * @param point1   The first point.
     * @param point2   The second point.
     * @return The distance between the two points in kilometers.
     */
    private static double calculateDistance(Point point1, Point point2) {
        double lat1 = Math.toRadians(point1.getLatitude());
        double lon1 = Math.toRadians(point1.getLongitude());
        double lat2 = Math.toRadians(point2.getLatitude());
        double lon2 = Math.toRadians(point2.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Earth radius in kilometers (you can adjust this based on your specific use case)
        double earthRadius = 6371.0;

        return earthRadius * c;
    }
}
