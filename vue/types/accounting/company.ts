export interface CustomerStateProps {
  customers: Customer[];
  error?: object | string | null;
}

export type Customer = {
  name: string;
  email: string;
  location: string;
  orders: number;
  date: string;
  status: number;
};

