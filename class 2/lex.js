const analyze = (value) => {
    let lToken = []
    let lError = []
    let state = 0
    let character = '#'
    let token = ''

    value += character

    const addToken = (typeToken) => {
        lToken.push({ token: token, typeToken: typeToken })
        state = 0
        token = ''
    }

    const addError = (c) => {
        lError.push({ message: 'Unexpected token', value: c })
        token = ''
        state = 0
    }

    for (let i = 0; i < value.length - 1; i++) {
        const c = value[i];

        switch (state) {
            case 0:
                if (c === '\n' || c === ' ') {
                    continue
                }
                else if (c === '=') {
                    token += c
                    addToken('EQUALS')
                } else if (c === '*') {
                    token += c
                    addToken('TIMES')
                } else if (c === '+') {
                    token += c
                    addToken('ADD')
                } else if (c.charCodeAt(0) >= 48 && c.charCodeAt(0) <= 57) {// 0-9
                    token += c
                    state = 1
                } else if ((c.charCodeAt(0) >= 65 && c.charCodeAt(0) <= 90) ||// A-Z
                    (c.charCodeAt(0) >= 97 && c.charCodeAt(0) <= 122)) {// a-z
                    token += c
                    state = 2
                }
                else {
                    addError(c)
                }
                break;

            case 1:
                if (c.charCodeAt(0) >= 48 && c.charCodeAt(0) <= 57) {
                    token += c
                    state = 1
                } else {
                    addToken('NUMBER')
                    i--
                }
                break;
            case 2:
                if ((c.charCodeAt(0) >= 65 && c.charCodeAt(0) <= 90) || // A-Z
                    (c.charCodeAt(0) >= 97 && c.charCodeAt(0) <= 122) || // a-z
                    (c.charCodeAt(0) >= 48 && c.charCodeAt(0) <= 57) || // 0-9
                    c === '_') {
                    token += c
                    state = 2
                } else {
                    addToken('ID')
                    i--
                }
                break
        }
    }

    return { lTokens: lToken, lError: lError }
}


console.log(analyze('posicion = inicial + veloci_Pdad * 60'))