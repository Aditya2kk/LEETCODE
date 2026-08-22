class Solution {
        public boolean checkDivisibility(int n) {
                    int originalN = n;
                            int digitSum = 0;
                                    int digitProduct = 1;

                                            int tempN = n;
                                                    while (tempN > 0) {
                                                                    int digit = tempN % 10;
                                                                                digitSum += digit;
                                                                                            digitProduct *= digit;
                                                                                                        tempN /= 10;
                                                    }

                                                            int divisor = digitSum + digitProduct;

                                                                    return originalN % divisor == 0;
        }
}