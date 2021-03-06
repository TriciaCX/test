package interviewTop100;

public class leetcode162
{
/**
 * 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
你可以假设 nums[-1] = nums[n] = -∞。
示例 1:
输入: nums = [1,2,3,1]
输出: 2
解释: 3 是峰值元素，你的函数应该返回其索引 2。
示例 2:

输入: nums = [1,2,1,3,5,6,4]
输出: 1 或 5 
解释: 你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6
 */
	public int findPeakElement(int[] nums) {
	   int maxIdx = 0;
       for(int i=0;i<nums.length;i++) {
    	  if(nums[maxIdx]<nums[i])
    		  maxIdx = i;
       }
       return maxIdx;
	}
	
	/*
	 * 线性扫描
	 * 本方法利用了连续的两个元素 nums[j] 和nums[j+1] 不会相等这一事实。于是，我们可以从头开始遍历nums 数组。
	 * 每当我们遇到数字 nums[i]，只需要检查它是否大于下一个元素nums[i+1] 即可判断nums[i] 是否是峰值。
	 */
	public int findPeakElementII(int[] nums) {
		for(int i=0;i<nums.length-1;i++) {
			if(nums[i]>nums[i+1])
				return i;
		}
		return nums.length-1;
	}
	
	/**
	 * 递归二分查找
	 * 我们可以将 nums数组中的任何给定序列视为交替的升序和降序序列。通过利用这一点，以及“可以返回任何一个峰作为结果”的要求，我们可以利用二分查找来找到所需的峰值元素。
在简单的二分查找中，我们处理的是一个有序数列，并通过在每一步减少搜索空间来找到所需要的数字。在本例中，我们对
二分查找进行一点修改。首先从数组 nums 中找到中间的元素 mid。
若该元素恰好位于降序序列或者一个局部下降坡度中（通过将 nums[i]与右侧比较判断)，则说明峰值会在本元素的左边。于是，我们将搜索空间缩小为 mid的左边(包括其本身)，并在左侧子数组上重复上述过程。
若该元素恰好位于升序序列或者一个局部上升坡度中（通过将 nums[i]与右侧比较判断)，则说明峰值会在本元素的右边。于是，我们将搜索空间缩小为 mid的右边，并在右侧子数组上重复上述过程。
就这样，我们不断地缩小搜索空间，直到搜索空间中只有一个元素，该元素即为峰值元素。
	 */
	public int findPeakElementIII(int[] nums) {
		return search(nums,0,nums.length-1);
	}

	private int search(int[] nums, int left, int right)
	{
		if(left==right)
			return left;
		int mid = (left+right)/2;
		if(nums[mid]>nums[mid+1])
			return search(nums, left, mid);
		return search(nums, mid+1, right);
	}
	
	public int findPeakElementIIII(int[] nums) {
		int left = 0;
		int right = nums.length-1;
		while(left<right) {
			int mid = left+(right-left)/2;
		    if(nums[mid]>nums[mid+1]) right = mid;
		    else left = mid+1;
		}
		return left;
	}
}
