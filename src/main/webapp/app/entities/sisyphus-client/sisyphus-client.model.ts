import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';

export interface ISisyphusClient {
  id?: number;
  name?: string | null;
  contact?: string | null;
  defaultFolder?: string | null;
  sisyphusClient?: ISisyphusJob | null;
}

export class SisyphusClient implements ISisyphusClient {
  constructor(
    public id?: number,
    public name?: string | null,
    public contact?: string | null,
    public defaultFolder?: string | null,
    public sisyphusClient?: ISisyphusJob | null
  ) {}
}

export function getSisyphusClientIdentifier(sisyphusClient: ISisyphusClient): number | undefined {
  return sisyphusClient.id;
}
