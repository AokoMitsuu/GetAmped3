	� G -/accs/thunder_halberd/mdl/thunder_halberd.kmd ��       �V[o�H~ƒ��	,}�����J���:Q�V�'4��2f�0&�����I{��`ֵ{_Y��|�9s.�/��{��(��ٹ@	�'�G�!'����~�l2ƅ~���N0�ؿ�{���)ݡ!q�VDH���x���\�^�R�HE�ý.=��\Z�������#AXz�:%�Z�$M�W�X1�-��� �nlq|?����pذ��pc�z�rӱ\���:��gG>�!��t���E�Pv�����/�yc����mkg"�J̣�yGG�:�����
F"� ���H*tZ�B�)CB�)c�$c���ٙ4�Pn7�l�0=�KDM�ě`�����`���$7'�W������r���V����jjb�M#̃5�7�GF	�i$6i���?~Խ״��Q4�m��o�[Jz��lO.�Q1�L�3 ���8� d�`�DG{S���Av��)��ث�V�*O���VZ��˪J���XO>u�_�q�M���f��2d�eG(�<�P�kq���pm�J��<7GU�h�c��i�u���2�F50~���Э��cc�Tg�ig�VO�?����I�^5O1II��P
�-��ӝ��8���ܨ�[��[>���Ǐ�x��լ5�<=�Ƅ|7�g����v����W��JȲ���X��J&ՌR��_�ê:�3!��n�R�O�&��$�l�ڹB�n�J�yns��o{?��n�@�*�{��s^=d؄���W�˕��9�T�/fˋ`���rL..j�Y�`3F0���z�"vw-�tjᴬ��ղ=�m��?�p,u�H+���8�É9?,1�i��'U:�����4��7IQ���)z��]gV;r�8r{�)����BP&�P��a�L��e2R|����C�/�p+��63,����ɕLI*��áx�/�J�Z�Dߠ9�ڞ�	�,��:������[�C_7N	�;��|:��]E�i�9��n��H{�   j  Lga2/stage/AmpedScript; e Lkotori/data/KGLGenerationData; lightning08D /efx/electric/lightning08.scm�    ?�      ?�  ?�  ?�               glow01_yellow2_3D /efx/flash/glow01_yellow2_3.ptc�    ?�      ?�  ?�  ?�               glow01_yellow5D /efx/flash/glow01_yellow5.ptc�    ?�      ?   ?   ?                Lkotori/gfx/KGLScriptModel; thunder_halberd_disk th_efx_core th_efx_color efx start reset F_x2 afterimage_level2 afterimage_level0 fadeout fadeout2 rev rev_end energy_charge_lv1 energy_charge_lv2 	start_add add_end Lkotori/scp/KotoriScript; � 
accumlimit
 � � 3 getStatusModifierFlags (ILjava/lang/String;)I
 f � � thunder_halberd n startScript (Ljava/lang/String;)Z
 � � � level rndc (F)F
 H � �=��� g hpb (FFF)V
 � � �C4   scale (F)V
 � � �>��� generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � start_yel_add2 /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �
 H 0 3 finishAllConnections
 d � 1D ./accs/thunder_halberd/thunder_halberd_disk.scm�    ?�      ?�  ?�  ?�              D %/accs/thunder_halberd/th_efx_core.scm�    ?�      ?�  ?�  ?�              D &/accs/thunder_halberd/th_efx_color.scm�    ?�      ?�  ?�  ?�               roll (Lkotori/geom/Vector3D;I)V
 f � �-             rotate (Lkotori/geom/Quaternion;)V
 f � ��� kotori.geom.Quaternion?�              -D�          -            ���;�.?�           setRenderType
 d � 3 setBlend
 d � 3 setEdge (Z)V
 f � � setShadowState
 d � 3 setColor (FFFF)V
 d � �>L�� 	fadeColor (FFFFI)V
 d � � kill
 f � 1-            ��'	��?�  �;�.�;�.��'	��?�  �;�.�;�.-D�          D /accs/thunder_halberd/efx.scm�    ?�      ?���?���?���             line_lv1D /accs/thunder_halberd/efx.scm�    ?�      ?���?���?���             line_lv2D ./accs/thunder_halberd/thunder_halberd_disk.scm�    ?�      ?�  ?�  ?�              AD %/accs/thunder_halberd/th_efx_core.scm�    ?�      ?�  ?�  ?�              AD &/accs/thunder_halberd/th_efx_color.scm�    ?�      ?�  ?�  ?�              A f h g j g l g o n p n q n - 1  i� k�m��  r 1  �>� �* ȶ �W*1�� �<� � <�� ���� �W�� ���� �W�� ���� �W� L�� �E� �� ��� ��� �� �W� �$b� �W*� �� �W�$b� �W*�� �W*�� �W� "� *�� �W*�� �W� *�� �W�W��>�  s 1  )*� �W*�s� ��*�s� ��*�s� ��*� W�  / 1  *� W�  t 1  *�� �W*�� �W*� W�  u 1  0*� �W*�� �W*d� �W*�� �W*�� �W*� �W*� W�  v 1  C*� �W*� �W*� �W*� �W*�� �W*� �W*� ȶ �W* ȶ �W*� �W�  w 1  A*� �W*� �W*� �W*� �W*� �W*� �W* ȶ �W* ȶ �W*� �W�  x 1  F*� �W*� �W*� �W*� �W*� �W*� �W*� �W* ȶ �W* ȶ �W*� �W�  y 1  D*� �W*� �W*� �W*� �W*� �W*� �W*� �W*d� �W*d� �W*� �W�  z 1  *�� �W*Ķ �W�  { 1  *Ŷ �W*�� �W* �� �W*� W�  | 1  
*�ȶ �W�  } 1  
*�ʶ �W�  ~ 1  E*� �W*� �W*� �W*� �W*� �W*� �W*�s� ��*�s� ��*�s� ���   1  F*� �W*� �W*� �W*� �W*� �W*� �W*� �W* ȶ �W* ȶ �W*� �W� �a�    >�y۽�E�=]�?��{>�#H�  