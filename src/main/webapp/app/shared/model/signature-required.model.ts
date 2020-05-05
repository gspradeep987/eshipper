export interface ISignatureRequired {
  id?: number;
}

export class SignatureRequired implements ISignatureRequired {
  constructor(public id?: number) {}
}
