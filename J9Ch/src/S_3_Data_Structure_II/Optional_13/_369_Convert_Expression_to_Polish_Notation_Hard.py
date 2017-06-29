

class Solution:
    # @param m: An integer m denotes the size of a backpack
    # @param A: Given n items with size A[i]
    def opOrder(self, op1,op2):
        order_dic = {'*':4, '/':4, '+':3, '-':3}
        if op1 == '(' or op2 == '(':
            return False
        elif op2 == ')':
            return True
        else:
            if order_dic[op1] <= order_dic[op2]:
                return False
            else:
                return True

    def convertToPN(self, expression):
        prefix = []
        stack = []
        string_tmp = []
        for s in expression[::-1]:
            if s == '(':
                string_tmp.append(')')
            elif s == ')':
                string_tmp.append('(')
            else:
                string_tmp.append(s)

        for s in string_tmp:
            if s.isdigit():
                prefix = [s] + prefix
            else:
                while len(stack)  and self.opOrder(stack[-1], s):
                    op = stack.pop()
                    prefix = [op] + prefix
                if len(stack) == 0 or s != ')':
                    stack.append(s)
                else:
                    stack.pop()
        if len(stack):
            prefix = stack + prefix

        return prefix

