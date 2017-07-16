from parser.ast import AST


class Type(AST):
    def __init__(self, token):
        self.token = token
        self.value = token.value
