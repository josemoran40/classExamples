function analyze(value) {
    let lTokens = []
    let lError = []
    let value2 = value + '#'
    let c = ''
    let state = 0
    let token = ""

    const addToken = (typeToken) => {
        lTokens.push({ value: token, typeToken: typeToken })
        token = ""
        state = 0
    }

    const addError = (c) => {
        lError.push({ value: c, message: 'Caracter desconcido' })
        token = ""
        state = ""
    }

    for (let i = 0; i < value2.length; i++) {
        c = value2[i];
        switch (state) {
            case 0:
                if (c === "\n" || c === " ") {
                    token = ""
                    state = 0
                } else if ((c.charCodeAt(0) >= 65 && c.charCodeAt(0) <= 90) ||// A-Z
                    (c.charCodeAt(0) >= 97 && c.charCodeAt(0) <= 122)) {
                    //[a - zA - Z].
                    state = 1
                    token += c
                } else if (c === "*") {
                    token += c
                    addToken("TIMES")
                } else if (c === "+") {
                    token += c
                    addToken("ADD")
                } else if (c === "=") {
                    token += c
                    addToken("EQUALS")
                }
                else if (c === "#") {
                    continue
                } else if ((c.charCodeAt(0) >= 48 && c.charCodeAt(0) <= 57)) {
                    token += c
                    //[0-9].[0-9]* = [0-9]+
                    state = 2
                }
                else {
                    addError(c)
                }

                break;

            case 1:
                if ((c.charCodeAt(0) >= 65 && c.charCodeAt(0) <= 90) ||// A-Z
                    (c.charCodeAt(0) >= 97 && c.charCodeAt(0) <= 122) ||
                    (c.charCodeAt(0) >= 48 && c.charCodeAt(0) <= 57) || c === "_") {

                    //([a - zA - Z] | [0 - 9] | _) *
                    token += c
                    state = 1
                } else {
                    addToken("ID")//2
                    i--
                }
                break;

            case 2:
                if ((c.charCodeAt(0) >= 48 && c.charCodeAt(0) <= 57)) {
                    token += c
                    state = 2
                } else if (c === ".") {
                    token += c
                    state = 3
                } else {
                    addToken("NUMBER")
                    i--
                }
                break;

            case 3:
                if ((c.charCodeAt(0) >= 48 && c.charCodeAt(0) <= 57)) {
                    token += c
                    state = 4
                } else {
                    addError(c)
                }
                break

            case 4:
                if ((c.charCodeAt(0) >= 48 && c.charCodeAt(0) <= 57)) {
                    token += c
                    state = 4
                } else {
                    addToken("DECIMAL")
                    i--
                }
                break

        }
    }//compi@
    /**
     * token = compi
     * c = @
     */
    return { lTokens: lTokens, lError: lError }

}

console.log(analyze("8/L:"))