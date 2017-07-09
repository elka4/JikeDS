

class Solution:
    # @param expression: a list of strings;
    # @return: an integer
    def getBlocks(self, delims, expression):
        paren, lastIndex = 0, 0
        blocks = []
        lastOperator = None
        for index, e in enumerate(expression):
            if e == '(':
                paren += 1
            elif e == ')':
                paren -= 1
            elif e in delims and paren == 0:
                blocks.append((lastOperator, expression[lastIndex: index]))
                lastIndex = index + 1
                lastOperator = e
        blocks.append((lastOperator, expression[lastIndex: len(expression)]))
        return blocks

    def evaluateExpression(self, expression):
        if len(expression) == 0:
            return 0
        if len(expression) == 1:
            return int(expression[0])

        blocks = self.getBlocks(['+', '-'], expression)
        if len(blocks) == 1:
            blocks = self.getBlocks(['*', '/'], expression)
            if len(blocks) == 1:  # must be ( ... )
                return self.evaluateExpression(expression[1:-1])

        sum = 0
        for opt, exp in blocks:
            val = self.evaluateExpression(exp)
            if opt is None:
                sum = val
            elif opt == '+':
                sum += val
            elif opt == '-':
                sum -= val
            elif opt == '*':
                sum *= val
            elif opt == '/':
                sum /= val
        return sum