


function encodePassword( textPass ) {

    var encodedHex = blowfish.encrypt( textPass , 'ever3769', {cipherMode: 0, outputType: 1});
    return encodedHex;

}