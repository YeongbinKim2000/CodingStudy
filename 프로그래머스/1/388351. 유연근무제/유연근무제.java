class Solution {
    public static int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int i = 0; i < schedules.length; i++) {
            int curTargetTime = calculateTime(schedules[i]);
            int cnt = 0;
            int curDay = startday;
            boolean fail = false;
            while (cnt < 7) {
                int todayTime = timelogs[i][cnt];
                cnt++;
                if (isWeekend(curDay)) {
                    curDay++;
                    if (curDay >= 8)
                        curDay = 1;
                    continue;
                }
                if (curTargetTime < todayTime) {
                    fail = true;
                    break;
                }
                curDay++;
                if (curDay >= 8)
                    curDay = 1;
            }
            if (!fail)
                answer++;
        }

        return answer;
    }

    private static boolean isWeekend(int day) {
        if (day == 6 || day == 7)
            return true;

        return false;
    }

    private static int calculateTime(int time) {
        int minute = time % 100;
        int hour = time / 100;

        minute += 10;
        if (minute >= 60) {
            minute -= 60;
            hour += 1;
        }

        return (hour * 100) + minute;
    }
}