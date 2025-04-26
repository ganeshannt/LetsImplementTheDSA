package com.practise.ds.tuf.advance;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearchLogic {


    public static void main(String[] args) {
        BinarySearchLogic solution = new BinarySearchLogic();
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(73, 79, 85, 90, 97, -97, -92, -89, -88, -82, -71, -62, -45, -37, -33, -29, -8, -4, 5, 18, 39, 46, 49, 61));  // Renamed arr to nums
        int ans = solution.findKRotation(nums);
        System.out.println("The array is rotated " + ans + " times.");
    }

    public int lowerBound(int[] nums, int x) {
        int start = 0;
        int end = nums.length - 1;
        int ans = nums.length;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] >= x) {
                ans = mid;
                end = mid - 1;
            } else if (nums[mid] < x) {
                start = mid + 1;
            }
        }
        return ans;
    }

    public int upperBound(int[] nums, int x) {
        int start = 0;
        int end = nums.length - 1;
        int ans = nums.length;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > x) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    public int[] getFloorAndCeil(int[] nums, int x) {
        return new int[]{getFloor(nums, x), getCeil(nums, x)};
    }

    public int getFloor(int[] nums, int x) {
        int start = 0;
        int end = nums.length - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] <= x) {
                ans = nums[mid]; // the floor will be stored because here mid-index always has lower than x.
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    public int getCeil(int[] nums, int x) {
        int start = 0;
        int end = nums.length - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= x) {
                ans = nums[mid]; //the ceil will be stored because here mid-index always has higher than x.
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    public int[] searchRange(int[] nums, int target) {
        int startIndex = getStartIndex(nums, target);
        // If the target is not present in the array
        if (startIndex == -1) return new int[]{-1, -1};

        int endIndex = getEndIndex(nums, target);

        return new int[]{startIndex, endIndex};
    }

    public int getStartIndex(int[] nums, int x) {
        int start = 0;
        int end = nums.length - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == x) {
                ans = mid;
                end = mid - 1;
            } else if (nums[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    public int getEndIndex(int[] nums, int x) {
        int start = 0;
        int end = nums.length - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == x) {
                ans = mid;
                start = mid + 1;
            } else if (nums[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    public int searchInARotatedSortedArrayI(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == k) {
                return mid;
            } else if (nums[start] <= nums[mid]) {
                if (nums[start] <= k && nums[mid] >= k) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[end] >= k && nums[mid] <= k) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return ans;
    }

    public boolean searchInARotatedSortedArrayII(ArrayList<Integer> nums, int k) {
        int start = 0;
        int end = nums.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums.get(mid) == k) {
                return true;
            }

            // Handle duplicates: if arr[low], arr[mid], and arr[high] are equal
            if (nums.get(start).equals(nums.get(mid)) && nums.get(mid).equals(nums.get(end))) {
                start = start + 1;
                end = end - 1;
                continue;
            }

            if (nums.get(start) <= nums.get(mid)) {
                if (nums.get(start) <= k && nums.get(mid) >= k) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums.get(end) >= k && nums.get(mid) <= k) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }

    public int findMin(ArrayList<Integer> nums) {
        int start = 0;
        int end = nums.size() - 1;
        int ans = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // if Array is sorted without rotation or
            // search space is already sorted then array[start] always smaller than array[end] this will break the loop

            if (nums.get(start) <= nums.get(end)) {
                ans = Math.min(ans, nums.get(start));
                break;
            }

            if (nums.get(start) <= nums.get(mid)) {
                ans = Math.min(nums.get(start), ans);
                start = mid + 1;
            } else {
                ans = Math.min(nums.get(mid), ans);
                end = mid - 1;
            }
        }
        return ans;
    }

    public int findKRotation(ArrayList<Integer> nums) {
        int start = 0;
        int end = nums.size() - 1;
        int ans = Integer.MAX_VALUE;
        int minIndex = 0;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // if Array is sorted without rotation or
            // search space is already sorted then array[start] always smaller than array[end] this will
            // break the loop

            if (nums.get(start) <= nums.get(end)) {
                if (ans > nums.get(start)) {
                    minIndex = start;
                    ans = nums.get(start);
                }
                break;
            }

            if (nums.get(start) <= nums.get(mid)) {
                if (ans > nums.get(start)) {
                    minIndex = start;
                    ans = nums.get(start);
                }
                start = mid + 1;
            } else {
                if (ans > nums.get(mid)) {
                    minIndex = mid;
                    ans = nums.get(mid);
                }
                end = mid - 1;
            }
        }
        return minIndex;
    }

    public int singleNonDuplicate(int[] nums) {
        int n = nums.length; // Size of the array.

        // Edge cases:
        if (n == 1) return nums[0];
        if (nums[0] != nums[1]) return nums[0];
        if (nums[n - 1] != nums[n - 2]) return nums[n - 1];

        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = (low + high) / 2;

            // If nums[mid] is the single element:
            if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) {
                return nums[mid];
            }

            // We are in the left part:
            if ((mid % 2 == 1 && nums[mid] == nums[mid - 1])
                    || (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {
                // Eliminate the left half:
                low = mid + 1;
            }
            // We are in the right part:
            else {
                // Eliminate the right half:
                high = mid - 1;
            }
        }

        // Dummy return statement:
        return -1;
    }

    public long floorSqrt(long n) {
        Double sqrt = Math.sqrt(n);
        if (sqrt == sqrt.intValue()) {
            return sqrt.longValue();
        } else {
            return sqrt.intValue();
        }
    }
}