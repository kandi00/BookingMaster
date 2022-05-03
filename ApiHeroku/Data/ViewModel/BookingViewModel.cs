namespace ApiHeroku.Data.ViewModel
{
    public class BookingViewModel
    {
        public DateTime from_date { get; set; }
        public DateTime to_date { get; set; }
        public int RoomId { get; set; }
        public int UserId { get; set; }
    }
}
