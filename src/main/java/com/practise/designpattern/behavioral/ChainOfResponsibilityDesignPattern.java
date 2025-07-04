package com.practise.designpattern.behavioral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Chain of Responsibility is a behavioral design pattern that lets you pass requests along a chain of handlers.
 * Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.
 * <p>
 * The Chain of Responsibility pattern allows a number of classes to attempt to handle a request independently.
 * <p>
 * Reference: https://refactoring.guru/design-patterns/chain-of-responsibility
 **/


// --- Supporting Classes (default access modifier) ---

/**
 * Represents the request being passed along the chain.
 * Simplified version of an HTTP request focus.
 */
class SimpleHttpRequest {
    String path;
    String username; // Populated if user is 'logged in'
    String role;     // User's role if logged in
    boolean isAuthenticated = false; // Status set by Authentication filter

    public SimpleHttpRequest(String path) {
        this.path = path;
    }

    // Helper method to simulate a login
    public void simulateLogin(String user, String userRole) {
        this.username = user;
        this.role = userRole;
        // Authentication filter will formally set isAuthenticated
    }

    @Override
    public String toString() {
        return "Request[Path='" + path + "', User='" + username + "', Role='" + role + "', Authenticated=" + isAuthenticated + "]";
    }
}

/**
 * Interface representing the chain of filters.
 * Allows a filter to pass the request to the next filter.
 */
interface SimpleFilterChain {
    void doFilter(SimpleHttpRequest request);
}

/**
 * Interface for a single filter (Handler) in the chain.
 */
interface SimpleFilter {
    void doFilter(SimpleHttpRequest request, SimpleFilterChain chain);
}

/**
 * Concrete Handler 1: Checks if the user is authenticated.
 * If not authenticated and accessing a protected resource, it stops the chain.
 */
class AuthenticationCheckFilter implements SimpleFilter {
    @Override
    public void doFilter(SimpleHttpRequest request, SimpleFilterChain chain) {
        System.out.println(" AUTH FILTER: Checking authentication for " + request.path);

        // Simulate checking session/token - if username exists, consider them potentially logged in
        if (request.username != null && !request.username.isEmpty()) {
            request.isAuthenticated = true; // Mark as authenticated
            System.out.println(" AUTH FILTER: User '" + request.username + "' is authenticated. Passing on.");
            chain.doFilter(request); // Pass to the next filter
        } else {
            request.isAuthenticated = false;
            System.out.println(" AUTH FILTER: User is NOT authenticated.");
            // Allow access only to public paths if not authenticated
            if (request.path.startsWith("/public")) {
                System.out.println(" AUTH FILTER: Public path allowed. Passing on.");
                chain.doFilter(request); // Pass to the next filter
            } else {
                System.out.println(" AUTH FILTER: *** ACCESS DENIED *** (Authentication Required). Stopping chain.");
                // Stop processing - do not call chain.doFilter()
            }
        }
    }
}

/**
 * Concrete Handler 2: Checks if the authenticated user has the correct role/permissions.
 * If permissions are not enough for the requested path, it stops the chain.
 */
class AuthorizationCheckFilter implements SimpleFilter {
    @Override
    public void doFilter(SimpleHttpRequest request, SimpleFilterChain chain) {
        System.out.println("  AUTHZ FILTER: Checking authorization for " + request.path);

        // Skip checks for public paths or if not authenticated (should be blocked earlier)
        if (request.path.startsWith("/public") || !request.isAuthenticated) {
            System.out.println("  AUTHZ FILTER: No specific role needed or already blocked. Passing on.");
            chain.doFilter(request); // Pass to the next filter
            return;
        }

        // Check for admin paths
        if (request.path.startsWith("/admin")) {
            if ("ADMIN".equalsIgnoreCase(request.role)) {
                System.out.println("  AUTHZ FILTER: ADMIN role sufficient. Passing on.");
                chain.doFilter(request); // Pass to the next filter
            } else {
                System.out.println("  AUTHZ FILTER: *** ACCESS DENIED *** (Insufficient Role: Need ADMIN, Has '" + request.role + "'). Stopping chain.");
                // Stop processing
            }
        }
        // Check for general user paths (any authenticated user is okay here)
        else if (request.path.startsWith("/user")) {
            System.out.println("  AUTHZ FILTER: Authenticated user sufficient. Passing on.");
            chain.doFilter(request); // Pass to the next filter
        } else {
            // Default pass for any other authenticated path not explicitly checked
            System.out.println("  AUTHZ FILTER: Path doesn't require specific role beyond authentication. Passing on.");
            chain.doFilter(request);
        }
    }
}


/**
 * Concrete Handler 3: Simple logging filter.
 * Demonstrates a filter that always passes the request along.
 */
class LoggingFilter implements SimpleFilter {
    @Override
    public void doFilter(SimpleHttpRequest request, SimpleFilterChain chain) {
        System.out.println("LOG FILTER: Request received for " + request.path);
        // Always pass the request to the next filter in the chain
        chain.doFilter(request);
        System.out.println("LOG FILTER: Request processing finished for " + request.path); // Log after chain returns
    }
}


/**
 * Implements the FilterChain. Holds the list of filters and manages progression.
 */
class SimpleFilterChainImpl implements SimpleFilterChain {
    private final List<SimpleFilter> filters;
    private int currentPosition = 0;

    public SimpleFilterChainImpl(List<SimpleFilter> filters) {
        // Create a defensive copy
        this.filters = new ArrayList<>(filters);
    }

    /**
     * The core Chain of Responsibility logic.
     * If there are more filters, call the next one. Otherwise, the chain ends.
     */
    @Override
    public void doFilter(SimpleHttpRequest request) {
        if (currentPosition < filters.size()) {
            SimpleFilter nextFilter = filters.get(currentPosition);
            currentPosition++; // Move to the next position BEFORE calling the filter
            nextFilter.doFilter(request, this); // Call the current filter
        } else {
            // If no more filters, the request would normally go to the application's controller/servlet
            System.out.println(">>> END OF CHAIN: Request successfully passed all filters. Target resource would be invoked now.");
        }
    }
}


// --- Main Public Class ---

/**
 * Demonstrates the Chain of Responsibility pattern using a simplified
 * security filter chain concept.
 */
public class ChainOfResponsibilityDesignPattern {

    public static void main(String[] args) {
        // 1. Define the filters in the desired order
        List<SimpleFilter> securityFilters = Arrays.asList(
                new LoggingFilter(),             // First, log the request
                new AuthenticationCheckFilter(), // Second, check if logged in
                new AuthorizationCheckFilter()   // Third, check roles/permissions
        );

        // --- Test Cases ---

        System.out.println("=== TEST CASE 1: Accessing Public Resource (Unauthenticated) ===");
        SimpleHttpRequest request1 = new SimpleHttpRequest("/public/info");
        SimpleFilterChain chain1 = new SimpleFilterChainImpl(securityFilters);
        chain1.doFilter(request1);
        System.out.println("=============================================================\n");

        System.out.println("=== TEST CASE 2: Accessing User Resource (Unauthenticated) ===");
        SimpleHttpRequest request2 = new SimpleHttpRequest("/user/dashboard");
        SimpleFilterChain chain2 = new SimpleFilterChainImpl(securityFilters);
        chain2.doFilter(request2); // Should be blocked by AuthenticationFilter
        System.out.println("=============================================================\n");

        System.out.println("=== TEST CASE 3: Accessing User Resource (Authenticated as USER) ===");
        SimpleHttpRequest request3 = new SimpleHttpRequest("/user/dashboard");
        request3.simulateLogin("Bob", "USER"); // Simulate user Bob being logged in
        SimpleFilterChain chain3 = new SimpleFilterChainImpl(securityFilters);
        chain3.doFilter(request3); // Should pass all filters
        System.out.println("=============================================================\n");

        System.out.println("=== TEST CASE 4: Accessing Admin Resource (Authenticated as USER) ===");
        SimpleHttpRequest request4 = new SimpleHttpRequest("/admin/settings");
        request4.simulateLogin("Bob", "USER"); // Bob is only a USER
        SimpleFilterChain chain4 = new SimpleFilterChainImpl(securityFilters);
        chain4.doFilter(request4); // Should be blocked by AuthorizationFilter
        System.out.println("=============================================================\n");

        System.out.println("=== TEST CASE 5: Accessing Admin Resource (Authenticated as ADMIN) ===");
        SimpleHttpRequest request5 = new SimpleHttpRequest("/admin/settings");
        request5.simulateLogin("Alice", "ADMIN"); // Alice is an ADMIN
        SimpleFilterChain chain5 = new SimpleFilterChainImpl(securityFilters);
        chain5.doFilter(request5); // Should pass all filters
        System.out.println("=============================================================\n");
    }
}