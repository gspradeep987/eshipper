import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';

export interface ISisyphusJobParam {
  id?: number;
  name?: string | null;
  value?: string | null;
  sisyphusJob?: ISisyphusJob | null;
}

export class SisyphusJobParam implements ISisyphusJobParam {
  constructor(public id?: number, public name?: string | null, public value?: string | null, public sisyphusJob?: ISisyphusJob | null) {}
}

export function getSisyphusJobParamIdentifier(sisyphusJobParam: ISisyphusJobParam): number | undefined {
  return sisyphusJobParam.id;
}
