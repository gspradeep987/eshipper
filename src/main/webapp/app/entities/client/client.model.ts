import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';

export interface IClient {
  id?: number;
  iD?: number | null;
  nAME?: string | null;
  cONTACT?: string | null;
  dEFAULTFOLDER?: string | null;
  client?: ISisyphusJob | null;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public iD?: number | null,
    public nAME?: string | null,
    public cONTACT?: string | null,
    public dEFAULTFOLDER?: string | null,
    public client?: ISisyphusJob | null
  ) {}
}

export function getClientIdentifier(client: IClient): number | undefined {
  return client.id;
}
