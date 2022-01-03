"""
You are given a string s representing an attendance record for a student where each character 
signifies whether the student was absent, late, or present on that day. 
The record only contains the following three characters:
    'A': Absent.
    'L': Late.
    'P': Present.
    
The student is eligible for an attendance award if they meet both of the following criteria:
    - The student was absent ('A') for strictly fewer than 2 days total.
    - The student was never late ('L') for 3 or more consecutive days.
Return true if the student is eligible for an attendance award, or false otherwise.
"""


class Solution(object):
    def check_record(self, s):
        """
        :type s: str
        :rtype: bool
        """
        late_count = 0
        absent_count = 0
        last_letter = None
        for letter in s:
            if letter == "A":
                absent_count += 1
                if absent_count >= 2:
                    return False
            if letter == "L":
                if last_letter == "L" or late_count == 0:
                    late_count += 1
                    if late_count >= 3:
                        return False
                else:
                    late_count = 0
            else:
                late_count = 0
            last_letter = letter
        return True


if __name__ == "__main__":
    print("Student Attendance Record I")
    sol = Solution()
    result = sol.check_record("PPALLPAA")
    print(f"Input: PPALLPAA \nOutput: {result}")
    result = sol.check_record("PPALLLP")
    print(f"Input: PPALLLP \nOutput: {result}")
    result = sol.check_record("PPALLPLP")
    print(f"Input: PPALLPLP \nOutput: {result}")
