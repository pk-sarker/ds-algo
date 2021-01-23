class BuyAndSellStockLE:

    def max_profit(self, prices):
        if not prices:
            return 0
        if len(prices) == 0:
            return 0

        min = None
        max_profit = 0

        for i in range(0, len(prices)):
            if not min:
                min = prices[i]
            elif prices[i] < min:
                min = prices[i]
            elif prices[i] - min > max_profit:
                max_profit = prices[i] - min
        if not max_profit:
            max_profit = 0

        return max_profit


sol = BuyAndSellStockLE()

print('\nInput: [7,2,9,1,6,7,2,3,9,0,3] \nOutput: {}'.format(sol.max_profit([7, 2, 9, 1, 6, 7, 2, 3, 9, 0, 3])))
print('\nInput: [1,2,3,4,5,6] \nOutput: {}'.format(sol.max_profit([1,2,3,4,5,6])))
print('\nInput: [6,5,4,3,2,1] \nOutput: {}'.format(sol.max_profit([6,5,4,3,2,1])))

