from ast.ast import AST


class Var(AST):
    def __init__(self, token):
        self.token = token
        self.value = token.value
