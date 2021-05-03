import { ISisyphusSubJob } from 'app/entities/sisyphus-sub-job/sisyphus-sub-job.model';

export interface ISisyphusJob {
  id?: number;
  iD?: number | null;
  nAME?: string | null;
  sCHEDULEMINUTE?: string | null;
  sCHEDULEHOUR?: string | null;
  sCHEDULEDAY?: string | null;
  sCHEDULEMONTH?: string | null;
  sHOULDRUNYN?: string | null;
  rETRIES?: number | null;
  mONITORSCHEDULEYN?: boolean | null;
  subJobs?: ISisyphusSubJob[] | null;
}

export class SisyphusJob implements ISisyphusJob {
  constructor(
    public id?: number,
    public iD?: number | null,
    public nAME?: string | null,
    public sCHEDULEMINUTE?: string | null,
    public sCHEDULEHOUR?: string | null,
    public sCHEDULEDAY?: string | null,
    public sCHEDULEMONTH?: string | null,
    public sHOULDRUNYN?: string | null,
    public rETRIES?: number | null,
    public mONITORSCHEDULEYN?: boolean | null,
    public subJobs?: ISisyphusSubJob[] | null
  ) {
    this.mONITORSCHEDULEYN = this.mONITORSCHEDULEYN ?? false;
  }
}

export function getSisyphusJobIdentifier(sisyphusJob: ISisyphusJob): number | undefined {
  return sisyphusJob.id;
}
